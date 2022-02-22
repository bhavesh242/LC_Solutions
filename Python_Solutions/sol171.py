class Solution171(object):
    def titleToNumber(self, columnTitle):
        """
        :type columnTitle: str
        :rtype: int
        """
        aVal = ord('A')-1
        col = 0
        for c in columnTitle : 
            col = col*26+ (ord(c) - aVal)
            
        return col
        