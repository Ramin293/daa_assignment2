# Measured results

Each row is the mean of five runs. Times are milliseconds; counts are mean per run.

## Random Access

| Structure | Position | n | m | Time (ms) | Accesses | Movements | Comparisons | Theory |
|---|---|---:|---:|---:|---:|---:|---:|---|
| DynamicArray | random | 100 | 10000 | 0.312 | 10,000 | 0 | 0 | Theta(m) |
| LinkedList | random | 100 | 10000 | 0.739 | 253,204 | 0 | 0 | Theta(m*n) |
| DynamicArray | random | 1,000 | 10000 | 0.056 | 10,000 | 0 | 0 | Theta(m) |
| LinkedList | random | 1,000 | 10000 | 2.964 | 2,492,748 | 0 | 0 | Theta(m*n) |
| DynamicArray | random | 10,000 | 10000 | 0.357 | 10,000 | 0 | 0 | Theta(m) |
| LinkedList | random | 10,000 | 10000 | 30.898 | 25,118,522 | 0 | 0 | Theta(m*n) |
| DynamicArray | random | 100,000 | 10000 | 0.066 | 10,000 | 0 | 0 | Theta(m) |
| LinkedList | random | 100,000 | 10000 | 327.810 | 250,540,042 | 0 | 0 | Theta(m*n) |

## Search

| Structure | Position | n | m | Time (ms) | Accesses | Movements | Comparisons | Theory |
|---|---|---:|---:|---:|---:|---:|---:|---|
| DynamicArray | random | 100 | 1000 | 0.300 | 74,209 | 0 | 74,209 | Theta(m*n) average |
| LinkedList | random | 100 | 1000 | 0.349 | 74,209 | 0 | 74,209 | Theta(m*n) average |
| DynamicArray | random | 1,000 | 1000 | 0.854 | 714,678 | 0 | 714,678 | Theta(m*n) average |
| LinkedList | random | 1,000 | 1000 | 1.537 | 714,678 | 0 | 714,678 | Theta(m*n) average |
| DynamicArray | random | 10,000 | 1000 | 3.430 | 7,137,728 | 0 | 7,137,728 | Theta(m*n) average |
| LinkedList | random | 10,000 | 1000 | 12.529 | 7,137,728 | 0 | 7,137,728 | Theta(m*n) average |
| DynamicArray | random | 100,000 | 1000 | 30.035 | 70,849,669 | 0 | 70,849,669 | Theta(m*n) average |
| LinkedList | random | 100,000 | 1000 | 122.529 | 70,849,669 | 0 | 70,849,669 | Theta(m*n) average |

## Insert

| Structure | Position | n | m | Time (ms) | Accesses | Movements | Comparisons | Theory |
|---|---|---:|---:|---:|---:|---:|---:|---|
| DynamicArray | front | 100 | 1000 | 1.696 | 0 | 601,620 | 0 | Theta(m*n) |
| DynamicArray | middle | 100 | 1000 | 0.426 | 0 | 551,620 | 0 | Theta(m*n) |
| LinkedList | front | 100 | 1000 | 0.088 | 1,000 | 1,000 | 0 | Theta(m) |
| LinkedList | middle | 100 | 1000 | 0.080 | 50,999 | 1,000 | 0 | Theta(m*n) |
| DynamicArray | front | 1,000 | 1000 | 0.344 | 0 | 1,501,780 | 0 | Theta(m*n) |
| DynamicArray | middle | 1,000 | 1000 | 0.245 | 0 | 1,001,780 | 0 | Theta(m*n) |
| LinkedList | front | 1,000 | 1000 | 0.030 | 1,000 | 1,000 | 0 | Theta(m) |
| LinkedList | middle | 1,000 | 1000 | 0.669 | 500,999 | 1,000 | 0 | Theta(m*n) |
| DynamicArray | front | 10,000 | 1000 | 2.695 | 0 | 10,510,740 | 0 | Theta(m*n) |
| DynamicArray | middle | 10,000 | 1000 | 1.241 | 0 | 5,510,740 | 0 | Theta(m*n) |
| LinkedList | front | 10,000 | 1000 | 0.027 | 1,000 | 1,000 | 0 | Theta(m) |
| LinkedList | middle | 10,000 | 1000 | 6.109 | 5,000,999 | 1,000 | 0 | Theta(m*n) |
| DynamicArray | front | 100,000 | 1000 | 20.169 | 0 | 100,500,500 | 0 | Theta(m*n) |
| DynamicArray | middle | 100,000 | 1000 | 10.176 | 0 | 50,500,500 | 0 | Theta(m*n) |
| LinkedList | front | 100,000 | 1000 | 0.017 | 1,000 | 1,000 | 0 | Theta(m) |
| LinkedList | middle | 100,000 | 1000 | 66.125 | 50,000,999 | 1,000 | 0 | Theta(m*n) |

## Remove

| Structure | Position | n | m | Time (ms) | Accesses | Movements | Comparisons | Theory |
|---|---|---:|---:|---:|---:|---:|---:|---|
| DynamicArray | front | 100 | 1000 | 0.176 | 1,000 | 49,500 | 0 | Theta(m*n) |
| DynamicArray | middle | 100 | 1000 | 0.035 | 1,000 | 24,500 | 0 | Theta(m*n) |
| LinkedList | front | 100 | 1000 | 0.055 | 1,000 | 1,000 | 0 | Theta(m) |
| LinkedList | middle | 100 | 1000 | 0.044 | 25,500 | 1,000 | 0 | Theta(m*n) |
| DynamicArray | front | 1,000 | 1000 | 0.186 | 1,000 | 499,500 | 0 | Theta(m*n) |
| DynamicArray | middle | 1,000 | 1000 | 0.142 | 1,000 | 249,500 | 0 | Theta(m*n) |
| LinkedList | front | 1,000 | 1000 | 0.027 | 1,000 | 1,000 | 0 | Theta(m) |
| LinkedList | middle | 1,000 | 1000 | 0.339 | 250,500 | 1,000 | 0 | Theta(m*n) |
| DynamicArray | front | 10,000 | 1000 | 3.221 | 1,000 | 9,499,500 | 0 | Theta(m*n) |
| DynamicArray | middle | 10,000 | 1000 | 1.597 | 1,000 | 4,499,500 | 0 | Theta(m*n) |
| LinkedList | front | 10,000 | 1000 | 0.025 | 1,000 | 1,000 | 0 | Theta(m) |
| LinkedList | middle | 10,000 | 1000 | 5.422 | 4,500,500 | 1,000 | 0 | Theta(m*n) |
| DynamicArray | front | 100,000 | 1000 | 30.216 | 1,000 | 99,499,500 | 0 | Theta(m*n) |
| DynamicArray | middle | 100,000 | 1000 | 15.010 | 1,000 | 49,499,500 | 0 | Theta(m*n) |
| LinkedList | front | 100,000 | 1000 | 0.010 | 1,000 | 1,000 | 0 | Theta(m) |
| LinkedList | middle | 100,000 | 1000 | 57.999 | 49,500,500 | 1,000 | 0 | Theta(m*n) |

## Heap Insert

| Structure | Position | n | m | Time (ms) | Accesses | Movements | Comparisons | Theory |
|---|---|---:|---:|---:|---:|---:|---:|---|
| MinHeap | na | 100 | 100 | 0.014 | 0 | 356 | 203 | O(n*log n) worst |
| MinHeap | na | 1,000 | 1000 | 0.041 | 0 | 3,474 | 2,198 | O(n*log n) worst |
| MinHeap | na | 10,000 | 10000 | 0.332 | 0 | 32,825 | 22,585 | O(n*log n) worst |
| MinHeap | na | 100,000 | 100000 | 1.833 | 0 | 392,965 | 229,125 | O(n*log n) worst |

## Heap Extract

| Structure | Position | n | m | Time (ms) | Accesses | Movements | Comparisons | Theory |
|---|---|---:|---:|---:|---:|---:|---:|---|
| MinHeap | na | 100 | 100 | 0.029 | 0 | 510 | 857 | O(n*log n) worst |
| MinHeap | na | 1,000 | 1000 | 0.122 | 0 | 8,342 | 14,993 | O(n*log n) worst |
| MinHeap | na | 10,000 | 10000 | 0.692 | 0 | 116,671 | 216,482 | O(n*log n) worst |
| MinHeap | na | 100,000 | 100000 | 7.357 | 0 | 1,500,867 | 2,831,787 | O(n*log n) worst |
