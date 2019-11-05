### Single-Threaded vs Multi-Threaded

We ran black.jpg, google.jpg and words.txt on single and multi-threaded servers with 30,100 and 1000 calls.

The multi-threaded Server finished successfully with no trials taking more than 12 seconds except 1000 calls on words.txt which had a out of memory error.

We then decided to not run the single threaded server with a 
1000 calls since it would be guaranteed to fail. The single threaded server took substantially longer on all trials and got a broken pipe error on 100 calls with words.txt.
 
 Using black.jpg
 
| Client Calls | Single-Thread Server | Multi-Thread Server |
|--------------|----------------------|---------------------|
| 30           | 2 seconds            | under 1 Second      |
| 100          | 7 seconds            | 2 to 3 Seconds      |
| 1000         | N/A                  | 3 to 4 Seconds      |

Using google.jpg

| Client Calls | Single-Thread Server   | Multi-Thread Server |
|--------------|------------------------|---------------------|
| 30           | 18 Seconds             | 2 Seconds           |
| 100          | 1 minute and 3 Seconds | 6 seconds           |
| 1000         | N/A                    | 10 seconds          |

Using words.txt

| Client Calls | Single-Thread Server | Multi-Thread Server            |
|--------------|----------------------|--------------------------------|
| 30           | 50 seconds           | 5 Seconds                      |
| 100          | Broken Pipe error    | 12 Seconds                     |
| 1000         | N/A                  | 52 Seconds/Out of Memory Error |