import sys, getopt
import logging
import timeit

from iteration_utilities import duplicates

from enum import Enum, unique

log = logging.getLogger(sys.argv[0].strip('.py'))

# we would like to test and get performance stats for different algorithms
@unique
class Algorithm(Enum):
    """
    Duplicate integer array detection algorithms

    given input constraints 1 <= a[i] and a[i] <= n  and n == len(a)
    * the only valid case with with no dups is range 1..n in any order
    * n==1 obviously has no duplicates
    """
    SORTED_SCAN_SHORTCUT = 'sort, scan for repeats, and break on first found'
    SORTED_DUPS_LEN = 'sort, filter creating dups list, and check length'
    SEQ_SUM = 'check sum against the well-known expression for sum of 1..n'
    ITER_UTIL = '"use iteration_utilities function for duplicates'
    SET_LEN = 'create set from array, eliminating dups, and check len == n'

def sort_scan(n,s):
    "for sorted array s, scan for repeats, breaking on first found"
    found = False
    for i in range(n-1):
        if s[i] == s[i+1]:
            found = True
            break # 9.0
    return found

sc_warn = True # only warn once for n == 1

def dups(n,a,algo=Algorithm.SEQ_SUM):
    """
    determine if int array a of length n has any dups, the using given algorithm

    see test_dups.py
    """
    # assert(0 < n) # we actually handle empty array, returning False
    assert(isinstance(n, int))
    assert(isinstance(a, list))
    assert(isinstance(algo,Algorithm))

    # input int array a of length n has only 1 <= a[i] <= n
    assert(n == len(a))
    assert(all( isinstance(a[i], int) \
            and (1 <= a[i] and a[i] <= n) \
            for i in range(n))) # 16 secs

    found = False # default True?

    log.info("array length {}".format(n))
    global sc_warn
    if n <= 1 and sc_warn:
        log.warning("short circuited array length {}".format(n))
        sc_warn = False
        return False # Duh!

    if algo==Algorithm.SEQ_SUM:
        found = sum(a) != int((n)*(n+1)/2) # 1.7 secs in initial tests
    elif algo == Algorithm.SET_LEN:
        found = n != len(set(a)) # 2.3 secs
    elif algo == Algorithm.ITER_UTIL:
        found = 0 < len(list(duplicates(a))) # 3.8 secs
    else:
        s = sorted(a)
        if algo==Algorithm.SORTED_SCAN_SHORTCUT:
            found = sort_scan(n,s) # 9.0 secs
        if algo==Algorithm.SORTED_DUPS_LEN:
            found = 0 < len([s[i] for i in range(n-1) if s[i] == s[i+1]]) # 10.6 secs

    return found

def main(argv):
    """
    duplicate detection with optional timing data for multiple algorithms
    """

    algo_names = str([key for key in Algorithm.__members__])
    help = argv[0] +' --help' \
                       +' --timing' \
                       +' --algo ' \
                       +algo_names
    timing = False
    algos = []

    try:
        opts, args = getopt.getopt(argv[1:],"hdta:",
                ["help","timing","debug","algo="])
    except getopt.GetoptError:
        print(help)
        sys.exit(2)

    log.debug(opts)
    for opt,arg in opts:
        if opt in ['-h','--help']:
            print(help)
            sys.exit(0)

        if opt in ['-d','--debug']:
            log.setLevel(logging.DEBUG)
            fhnd = logging.FileHandler(argv[0].replace('.py','.log'))
            f = logging.Formatter(fmt='%(asctime)s %(name)s %(levelname)s %(message)s')
            fhnd.setFormatter(f)
            log.addHandler(fhnd)

        if opt in ['-t','--timing']:
            timing = True

        if opt in ['-a','--algo']:
            algos.append(Algorithm[arg])
    

    inputs = input().rstrip().split()
    a = list(map(int, inputs))
    n = len(a)
    
    if 0 == len(algos):
        # algos ordered roughly by expected time complexity
        algos = [Algorithm.SEQ_SUM
                ,Algorithm.SET_LEN
                ,Algorithm.ITER_UTIL
                ,Algorithm.SORTED_SCAN_SHORTCUT
                ,Algorithm.SORTED_DUPS_LEN
                ]

    for algo in algos:
        result = "dups {} found".format("were" if  dups(n,a,algo) else "not")

        # perform some timings to compare algorithms
        if timing:
            print(timeit.timeit("dups(len(a),a,algo)",
                globals={"dups":dups,"a":a, "algo": algo}),
                str(algo)+':', result)
        else:
            print(str(algo)+':', result)

if __name__ == '__main__':
    main(sys.argv)
