class Solution1011(object):
    def shipWithinDays(self, weights, days):
        """
        :type weights: List[int]
        :type days: int
        :rtype: int
        """
        
        l = 0
        r = 0
        for w in weights:
            r = r+w
            l = max(l, w)
        
        return self.bin_search(weights, days, l, r)
        
    def bin_search(self, weights, days, l, r):
        while l<r:
            mid = l+(r-l)/2
            if self.is_feasible(weights, days, mid):
                r = mid
            else:
                l = mid+1
                
        return l
        
    def is_feasible(self, weights, days, capacity):
        rem_capacity = capacity
        rem_days = days
        for i in range(len(weights)-1):
            if rem_days == 0 or weights[i] > capacity:
                return False
            rem_capacity = rem_capacity - weights[i]
            if(rem_capacity < weights[i+1]):
                rem_capacity = capacity
                rem_days = rem_days-1
        
        return rem_capacity >= weights[-1] and rem_days >= 1