from heapq import heappush, heappop

class Solution295:

    class MedianFinder:
        
        def __init__(self):
            self.min_heap = []
            self.max_heap = []

        def addNum(self, num: int) -> None:
            heappush(self.min_heap, num)
            heappush(self.max_heap, -heappop(self.min_heap))

            if len(self.min_heap) < len(self.max_heap):
                heappush(self.min_heap, -heappop(self.max_heap))

        def findMedian(self) -> float:
            if len(self.min_heap) != len(self.max_heap):
                return self.min_heap[0]
            else:
                return self.min_heap[0]/2 - self.max_heap[0]/2

