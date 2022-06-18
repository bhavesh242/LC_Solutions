class Solution1615(object):
    def maximalNetworkRank(self, n, roads):
        """
        :type n: int
        :type roads: List[List[int]]
        :rtype: int
        """

        # calculate all node degrees
        degrees = [0]*n
        for road in roads:
            degrees[road[0]] += 1
            degrees[road[1]] += 1

        # find first and second maximum degrees
        firstMax = 0
        secondMax = 0
        for degree in degrees:
            if degree > firstMax:
                secondMax = firstMax
                firstMax = degree
            elif degree > secondMax and degree < firstMax:
                secondMax = degree

        # find count of nodes that have first and second maximum degrees
        # multiple nodes can have first and second maximum degrees
        fmCt = 0
        smCt = 0

        for degree in degrees:
            if degree == firstMax:
                fmCt += 1
            if degree == secondMax:
                smCt += 1
        # if there are more than one node with degree = 'firstMax'
        # then we can consider 'firstMax' twice and we don't need to use 'secondMax'
        if fmCt > 1:
            """
             here the answer in general will be 2*firstMax
             but if the two nodes that we're considering are connected, then we'll have to subtract 1
             in other words, if there are atleast one such pair, whose degree = 'firstMax' and
             they're not connected then answer is 2*firstMax, else if all nodes with degree = 'firstMax'
             are connected to each other, then answer is 2*firstMax-1, as we'll have one common edge
            """
            edgeCt = 0
            for road in roads:
                if degrees[road[0]] == firstMax and degrees[road[1]] == firstMax:
                    edgeCt += 1
            # maximum number of edges possible with all the nodes with degree = 'firstMax'
            maxEdgeCt = (fmCt * (fmCt-1))/2
            # if all nodes with degree = 'firstMax' are connected to each other, then subtract 1
            return 2*firstMax - (1 if maxEdgeCt == edgeCt else 0)
            """
            if there is only one node with degree = 'firstMax'
            then we'll need to use node with degree = 'secondMax'
            """
        else:
            edgeCt = 0
            for road in roads:
                if degrees[road[0]] == firstMax and degrees[road[1]] == secondMax:
                    edgeCt += 1
                if degrees[road[0]] == secondMax and degrees[road[1]] == firstMax:
                    edgeCt += 1

            # if all pairs are connected to each other, then subtract 1
            return firstMax + secondMax - (1 if smCt == edgeCt else 0)
