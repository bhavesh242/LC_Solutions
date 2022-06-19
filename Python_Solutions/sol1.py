class Solution1(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        dict = {}
        
        for idx, element in enumerate(nums):
            if dict.has_key(element):
                return [dict.get(element), idx]
            
            dict[target-element] = idx
            
        return None