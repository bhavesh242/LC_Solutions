class Solution205(object):
    def isIsomorphic1(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        
        
        map_s = {}
        map_t = {}
        
        for S, T in zip(s,t):
            if (S not in map_s) and (T not in map_t):
                map_s[S] = T
                map_t[T] = S
                
                
            elif map_s.get(S)!=T or map_t.get(T)!=S:
                return False
            
        return True
        
        
        
    def isIsomorphic2(self, s, t):
        d = {}
        for i, ch in enumerate(s):
            if ch not in d:
                if t[i] in d.values():
                    return False
                d[ch] = t[i]
            else:
                if t[i] != d[ch]:
                    return False
        return True
    
    
    def isIsomorphic(self,s,t):
        return len(set(s)) == len(set(t)) == len(set(zip(s,t)))