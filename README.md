# point-of-sale scanning API
 by Andrew Liu
 ## 1.instruction
 ## 2.test case

## instruction

Used strategy design pattern to calculate the price.

right now I used Singleton for service class. because it is only execute by one customer right now. and it is mulithread-safe.

In the future, for extensive purpose, we can have a HashMap<User, Service> to process the request for mulitple customers.

And for the state transition of App class, it also could be implemented by state design pattern

### 1.Run this code

```bash
$ javac src/*.java
$ cd src/
$ java Main
```

```

When the application starts, it will print the main with price.
continue -> input product code -> input 'r' to get the total price
set -> set the Unit Price, Volume Size(optional) or Volume Price(optional).
exit -> terminate this process
```

```bash
Product Code | Price
A                        |$2.0 each or 4 for $7.0
B                        |$10.0 each
C                        |$1.25 each or 6 for $6.0
D                        |$0.15 each or 4 for $7.0
E                        |$2.0 each AND if buy one B get one E
Please enter 'continue' to continue with current pricing or 'set' to add new pricing
Please enter 'exit' to exit this program

```

### 2.Continuing with exiting pricing

continue with existing price enter `continue`
continue -> input product code -> input 'r' to get the total price
```bash
>>>continue
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>ABCDE
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
The total price is $14.74
Please enter 'continue' to continue with current pricing or 'set' to add new pricing
Please enter 'exit' to exit this program
>>>

```

before enter 'r', you can scan as much as you want to the cart
```bash
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>A
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>B
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>C
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
The total price is $14.575
Please enter 'continue' to continue with current pricing or 'set' to add new pricing
Please enter 'exit' to exit this program
```

if the cart is empty, you don't need to check out
```bash
Please enter 'continue' to continue with current pricing or 'set' to add new pricing
Please enter 'exit' to exit this program
>>>continue
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
no select
Invalid order!!! do it again!
```

if you entered unexist product code(it will be double checked in service class)
```bash
>>>ABCDF
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>Invalid product code(Only A - E)

```

the checkout will not success when the stock is not enough
```bash

Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>AAA
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
Stock of A is  not enough for this order!   Stock for this product :2
```
### 3.Continuing with another order

```bash
Please enter 'continue' to continue with current pricing or 'set' to add new pricing
Please enter 'exit' to exit this program
>>>continue
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>

```

### 4. update a new price

set -> set the Unit Price, Volume Size(optional) or Volume Price(optional).

```bash
>>>set
Enter new Pricing in the format <Product Code> <Per Unit Price> <Volume Size(optional)> <Volume Price(optional)>, split with one space
>>>

```

For example, enter A 9.9
if the code executed sucessed, the menu will show again to indict the next step.
```bash
>>>A 9.9
Product Code | Price
A                        |$9.9 each or 4 for $7.0
B                        |$10.0 each
C                        |$1.25 each or 6 for $6.0
D                        |$0.15 each or 4 for $7.0
E                        |$2.0 each AND if buy one B get one E
Please enter 'continue' to continue with current pricing or 'set' to add new pricing
Please enter 'exit' to exit this program
>>>

```

If we want to update the volunm size and volunm price, volunm size and volunm price are optional


```bash
>>> >>>A 9.9 10 86.6
Product Code | Price
A                        |$9.9 each or 10 for $86.6
B                        |$10.0 each
C                        |$1.25 each or 6 for $6.0
D                        |$0.15 each or 4 for $7.0
E                        |$2.0 each AND if buy one B get one E

```
use regex to check if the input is vaild

```bash
>>>FF 9.9 9.9 9.9
NO MATCH!! Invalid input
>>>F 9.9 99 99.9
NO MATCH!! Invalid input
```

### 5. EXIT
exit -> terminate this process
```bash
>>>exit //then the execution will be terminated
```

## test case
Scan these items in this order: ABCDABAA; Verify the total price is $31.24.
```bash
>>>ABCDABAA
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
The total price is $31.24
```
Scan these items in this order: CCCCCC; Verify the total price is $6.6.

```bash
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>CCCCCC
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
The total price is $6.6
```
Scan these items in this order: ABCD; Verify the total price is $14.74.
Because get 1 B will get 1 E free, so the total price for ABCDE is also $14.74.
```bash
>>>ABCD
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
The total price is $14.74


Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>ABCDE
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
The total price is $14.74
```
Scan these items in this order: ACDE; Verify the total price is $5.94. 
```bash
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>ACDE
Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)
>>>r
The total price is $5.94
```

