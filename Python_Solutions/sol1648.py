class Solution1648:
    def maxProfit(self, inventory: List[int], orders: int) -> int:
        #sort the array
        inventory.sort()

        idx = len(inventory)-1
        balls_covered = 1
        result = 0

        while (idx > 0 and orders >= balls_covered * (inventory[idx] - inventory[idx-1])):
            orders = orders - balls_covered * (inventory[idx] - inventory[idx-1])
            value_per_ball = self.get_difference(inventory[idx], inventory[idx-1])
            result = result + balls_covered*value_per_ball    
            result = result%1000000007
            balls_covered = balls_covered + 1
            idx = idx - 1

        
        if orders > 0:
            div = orders//balls_covered
            rem = orders % balls_covered
            result=result + balls_covered*self.get_difference(inventory[idx], inventory[idx]-div)
            result = result%1000000007
            result = result + rem* (inventory[idx] - div)
            result = result%1000000007

        return result%1000000007


    def get_difference(self, a , b):
            return a*(a+1)//2 - b*(b+1)//2
