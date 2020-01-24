

I'm certain that you can't solve this problem without going through the whole array, at least if you don't have any additional information (like the elements being sorted and restricted to certain values), so the problem has a minimum time complexity of O(n). You can, however, reduce the memory complexity to O(1) with a XOR-based solution, if every element is in the array an even number of times, which seems to be the most common variant of the problem, if that's of any interest to you:

```java
int unique(int[] array)
{
    int unpaired = array[0];
    for(int i = 1; i < array.length; i++)
        unpaired = unpaired ^ array[i];
    return unpaired;
}
```java

Basically, every XORed element cancels out with the other one, so your result is the only element that didn't cancel out.

