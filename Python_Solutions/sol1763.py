class Solution1763(object):
    def longestNiceSubstring(self, s):
        """
        :type s: str
        :rtype: str
        """
        
        upper = [0] * 26
        lower = [0] * 26
        
        for letter in s:
            if ord(letter) >= 97:
                upper[ord(letter) - 97] = 1
            else:
                lower[ord(letter) - 65] = 1
        
        flag = True
        breaking = '#'
        for i in range(26):
            if lower[i] != upper[i]:
                flag = False
                breaking = chr(i+97) if upper[i] == 1 else chr(i+65)
                break
        
        if flag:
            return s
        
        
        s1 = ""
        s2 = ""
        
        for i in range(len(s)):
            if s[i] == breaking:
                s1 = self.longestNiceSubstring(s[0:i])
                s2 = self.longestNiceSubstring(s[i+1: len(s)])
                return s2 if len(s1) < len(s2) else s1
        
        return ""