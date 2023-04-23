class Solution2646(object):
    def minimumTotalPrice(self, n, edges, price, trips):
        """
        :type n: int
        :type edges: List[List[int]]
        :type price: List[int]
        :type trips: List[List[int]]
        :rtype: int
        """

        def buildTree(n, edges):
            graph = []

            for i in range(n):
                graph.append([])
            

            for edge in edges:
                graph[edge[0]].append(edge[1])
                graph[edge[1]].append(edge[0])

            return graph

        def makeTrip(graph, node, parent, dst, visited_count):
            if node == dst:
                visited_count[node] = visited_count[node] + 1
                return True
            
            for adj in graph[node]:
                if adj == parent:
                    continue

                result = makeTrip(graph, adj, node, dst, visited_count)
                if result:
                    visited_count[node] = visited_count[node] + 1
                    return result
            
            return False
        
        def minimumPriceDfs(graph, contributions, dp, node, parent, canHalf):
            if dp[node][canHalf]!= None:
                return dp[node][canHalf]

            notHalved = contributions[node]
            for adj in graph[node]:
                if adj == parent:
                    continue
                notHalved+=minimumPriceDfs(graph, contributions, dp, adj, node, 1)

            halved = float('inf')
            if canHalf == 1:
                halved = contributions[node]//2
                for adj in graph[node]:
                    if adj == parent:
                        continue
                    halved+=minimumPriceDfs(graph, contributions, dp, adj, node, 0)

            dp[node][canHalf] = min(notHalved, halved)    
            return dp[node][canHalf]

        graph = buildTree(n, edges)
        visited_count = [0] * n
        for trip in trips:
            makeTrip(graph, trip[0], -1, trip[1], visited_count)
        
        contributions = [visited_count[i]* price[i] for i in range(n)]

        dp = []
        for i in range(n):
            dp.append([None, None])

        return minimumPriceDfs(graph, contributions, dp, 0, -1, 1)
