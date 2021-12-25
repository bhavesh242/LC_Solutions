class Solution231(object):
    def isPowerOfTwo(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n == 0:
            return False
        return n & (-n) == n
        

if __name__ == '__main__':
    sol231 = Solution231()
    print(sol231.isPowerOfTwo(3)) 