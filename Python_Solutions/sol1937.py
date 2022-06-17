class Solution1937(object):
    def maxPoints(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        n = len(points[0])
        dp = [0]*n
        
        for row in points:
            for index,element in enumerate(row):
                dp[index]=dp[index]+element
            
            for index in range(1,n):
                dp[index] = max(dp[index], dp[index-1]-1)
            
            for index in range(n-2,-1,-1):
                dp[index] = max(dp[index], dp[index+1]-1)
        
        result =0
        for i in range(n):
            result = max(result, dp[i])
            
        return result
            
            