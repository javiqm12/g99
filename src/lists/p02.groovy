package lists

//  P01 Find the last but one element of a list.
//  Example:
//      penultimate([1, 1, 2, 3, 5, 8])
//      5

// Using builtin negative index :-)
def penultimate(List ls) {
    ls[-2]
}

assert 5 == penultimate([1, 1, 2, 3, 5, 8])
