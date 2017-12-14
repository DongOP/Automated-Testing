"""
Bubble Sort
"""
array = [5,2,6,-7,-1,3]

length = len(array) - 1

for i in range(length,0,-1):
    for j in range(i):
        if array[j] > array[j+1]:
            array[j],array[j+1] = array[j+1],array[j]

print array

# The second method to Bubble Sort

# l = [5,2,6,-7,-1,3]

# for i in range(len(l),0,-1):
#     for j in range(len(l)-1):
#         if l[j] > l[j+1]:
#             tmp = l[j]
#             l[j] = l[j+1]
#             l[j+1] = tmp

# print l