class Solution(object):
    #Solution 1 using Stacks
    def decodeString1(self, s):
        """
        :type s: str
        :rtype: str
        """
        num_stack = []
        str_stack = []
        index = 0
        res= ""
        while(index < len(s)):
            if s[index].isdigit():
                count = 0
                while s[index].isdigit():
                    count = count*10 + int(s[index])
                    index+=1
                num_stack.append(count)
            elif s[index] == '[':
                str_stack.append(res)
                res = ""
                index+=1
            elif s[index] == ']':
                temp = str_stack.pop()
                count = num_stack.pop()
                res = res*count
                res = temp + res
                index+=1
            else:
                res = res + s[index]
                index=index+1
            
        return res
    
    #Solution 2 using Recusion
    def decodeString(self, s):
        return self.recurse(s,0)[0]
    
    def recurse(self, s, idx):
        
        res = ""
        ct = 0
        while idx < len(s):
            if s[idx].isdigit():
                ct = 0
                while s[idx].isdigit():
                    ct = ct*10 + int(s[idx])
                    idx=idx+1
            elif s[idx] == '[':
                temp, index = self.recurse(s, idx+1)
                res = res + temp*ct
                idx = index
            elif s[idx] == ']':
                return res,idx+1
            else:
                res = res + s[idx]
                idx = idx+1
        
        return res, idx
    