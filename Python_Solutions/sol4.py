from math import inf
class Solution4:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        
        m = len(nums1)
        n = len(nums2)
        if m > n:
            return self.findMedianSortedArrays(nums2, nums1)
        
        half = (m+n+1)//2
        lo = 0
        hi = m
        
        while lo<=hi:
            cut1 = (lo+hi)//2
            cut2 = half - cut1

            l1 = -inf if cut1 == 0 else nums1[cut1-1]
            r1 = inf if cut1 == m else nums1[cut1]
            l2 = -inf if cut2 == 0 else nums2[cut2-1]
            r2 = inf if cut2 == n else nums2[cut2]

            if l1 > r2:
                hi = cut1-1
            elif l2 > r1:
                lo = cut1+1
            else:
                if(m+n)%2 == 0:
                    return (max(l1,l2) + min(r1,r2))/2
                else:
                    return max(l1, l2)
        
        raise Exception("Illegal Arguments")