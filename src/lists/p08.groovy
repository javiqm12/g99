package lists

//  P08 Eliminate consecutive duplicates of list elements.
//  Example:
//      compress([1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5])
//      [1, 2, 3, 1, 4, 5]

List compressIterative(List ls) {
    List result = []
    for (int i = 0; i < ls.size(); i++) {
        result << ls[i]
        boolean duplicateFound = false
        int j = i + 1
        for (; j < ls.size() && ls[i] == ls[j]; j++)
            duplicateFound = true
        if (duplicateFound)
            i = j - 1
    }
    result
}

List compress(List ls) {
    if (!ls) return ls
    [ls.head()] + compress(ls.tail().dropWhile {it == ls.head()})
}

import groovy.transform.TailRecursive
@TailRecursive
List compressTailRecursive(List ls, result = []) {
    if (!ls) return result
    compressTailRecursive(ls.tail().dropWhile {it == ls.head()},
        result << ls.head())
}

List compressFunctional(List ls) {
    ls.inject([]) { ll, e ->
        if (ll)
            if (ll.last() != e) ll << e
            else ll
        else [e]
    }
}

List compressCollect(List ls) {
    def prev = null
    ls.collect{item -> 
        if (item!=prev) {
            prev=item
        } else {
            null
        }    
    } - [null]
}

List ls = [1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5]

assert compress(ls) == [1, 2, 3, 1, 4, 5]
assert compress([]) == []
assert compress([5]) == [5]
assert compress([3,3]) == [3]
assert compress(['a','a']) == ['a']

assert compressTailRecursive(ls) == [1, 2, 3, 1, 4, 5]
assert compressFunctional(ls) == [1, 2, 3, 1, 4, 5]
