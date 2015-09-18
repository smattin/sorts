from collections import Counter,ChainMap
from random import randint
"""
given list of ints, find random index of the max element
find one of 4,6,7 (indices of 6 in list)
"""
given = [-1,0,3,4,6,5,6,6]

def wb1(g):
    """ whiteboard question 1 """
    c = Counter(g)
    m1 = c.most_common(1)
    m = m1[0][0]
    q = m1[0][1] # count of m in g
    r = randint(1,q)
    i=-1
    for _ in range(r):
        i = g.index(m,i+1)
    assert i in [4,6,7] # only for given sample
    return i

print(wb1(given))
