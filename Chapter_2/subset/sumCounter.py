def sums_of(start, end, count, sum, want_sum, lst):
    #lst[count] = start
    #if count == 0:
    #    if sum == want_sum:
    #        print lst
    #        return 1
    #    return 0

    if count == 1:
        if want_sum - sum < end and want_sum - sum >= start:
            #lst[0] = want_sum - sum
            #print lst
            return 1
        else:
            return 0
    else:
        x = 0
        for n in xrange(start, end):
            if sum + n > want_sum:
                break
            lst[count-1] = n
            t = sums_of(n+1, end, count-1, sum+n, want_sum, lst)
            if t > 0:
                #print n
                x+=t
                t = 0
            lst[count-1] = 0
        return x


total = 0
for i in xrange(1, 15):
    t = sums_of(1, 16, i, 0, 30, [0]*14)
    print i, ": ", t
    total += t

print total
