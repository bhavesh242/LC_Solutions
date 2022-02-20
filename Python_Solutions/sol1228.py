class Solution1228(object):
    def removeCoveredIntervals(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: int
        """
        
        intervals.sort(key = lambda x : (x[0], -x[1]))
        
        count = 0
        earliest = 0
        
        for _, end in intervals:
            if(end <= earliest):
                continue;
            count += 1
            earliest = end
        
        return count