for y in range(1, 10):
    s = ''
    for i in map(lambda x, y:(x, y, x*y), range(1, y+1), [y]*y):
        s += ('%d*%d=%d\t' %i)
    print(s)