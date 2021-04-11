# Concurrent Batch HTTP Request Processor for Eluv.io API

This application is a Java utility that makes a throttled API call to Eluv.io. The limitation of utility is 5 concurrent
HTTP calls (5 threads).

## How the Program Works

The HTTP call format is
```curl https://eluv.io/items/cRF2dvDZQsmu37WGgK6MTcL7XjH -H "Authorization: Y1JGMmR2RFpRc211MzdXR2dLNk1UY0w3WGpI"```

The program uses the Java executor service and HttpClient with thread pools to throttle the call to a maximum of 5
concurrent calls. If the number of itemIDs is over 5, the threads will be mostly busy making concurrent calls.

## How to Run the Program in IntelliJ IDEA

Program Arguments: Set program arguments to a positive integer. Run the code from the main class - a green arrow in the
top right will appear, press the green arrow to run the program.

## Performance Test Results

| Test Number | Number of Items | Time (Seconds) |
| :---    |  :----:  |  ---:|
| Test 1  | 1 | 3 |  
| Test 2  | 5 | 7 |  
| Test 3  | 10 | 12 |  
| Test 4  | 20 | 23 | 
| Test 5  | 100 | 112 |  

A single core takes 3 seconds, however when the requests increase, the average time per item decreases. In conclusion, the tests demonstrate that the limit of 5 concurrent HTTP calls is functional.

## Technology Stack

* Java 11
* Maven

## Contacts

If you have any questions regarding this project, please locate me at:

* brianzou03@gmail.com
* https://github.com/brianzou03
* https://www.linkedin.com/in/brian-zou-8bbab4203/