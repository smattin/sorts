import sys
import unittest

from dups import dups,Algorithm

class TestDups(unittest.TestCase):
    def test_wrong_n(self):
        with self.assertRaises(AssertionError):
            dups(0,[1])

    def test_array_0_elements(self):
        for algo in Algorithm:
            self.assertFalse(dups(0,[],algo))

    def test_array_1_element(self):
        for algo in Algorithm:
            self.assertFalse(dups(1,[1],algo))

    def test_short_non_sequence(self):
        for algo in Algorithm:
            self.assertTrue(dups(3,[1,2,1],algo))

    def test_short_sequence(self):
        for algo in Algorithm:
            self.assertFalse(dups(3,[1,2,3],algo))

    def test_long_sequence(self):
        with open('test_seq.txt', 'r') as test_input:
            inputs = test_input.readline().rstrip().split()
            a = list(map(int, inputs))
            n = len(a)

        for algo in Algorithm:
            self.assertFalse(dups(n,a,algo))

    def test_long_non_sequence(self):
        with open('test_nseq.txt', 'r') as test_input:
            inputs = test_input.readline().rstrip().split()
            a = list(map(int, inputs))
            n = len(a)
    
        for algo in Algorithm:
            self.assertTrue(dups(n,a,algo))

    def test_non_integer(self):
        with self.assertRaises((AssertionError,TypeError)):
            #, "input array has a non-integer element"):
            dups(3,[1,2,'three'])

if __name__ == '__main__':
        unittest.main(argv=['first-arg-is-ignored'], exit=False)
