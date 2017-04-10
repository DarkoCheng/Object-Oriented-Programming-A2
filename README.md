# Object-Oriented-Programming-A2
CIS 2430 A2

The program is based on the assignment 1 that prof Fei Song uploaded onto course link.

(1) the general problem you are trying to solve:
My program is a library search program and it can add and search form a library.

(2) what are the assumptions  and  limitations  of  your  solution:
1.My program can only accept one file name on command line as input file, then the output file hardcoded, which is namend output.txt
2. My program can create the hash map, and put the key and value into it, but can't use it as search so far.

(3)  how  can  a  user  build  and  test  your  program (also  called  the  user  guide):
The user can simply follow the introductions while the program is running.

(4) how is the program tested for correctness:
a, I tried many times to input file into the program, and it can search all of them.
b, For the hash map party, even though it cannot be use as search method, but I personlly use hasmap get method and I did print the result
   I can get the value which is the possition in the references array list, so which means the hash map is working, but I did not figure out
   how can I use it in the search part yet.
c, After the program loads the file, I also tried to add the references into the program manually and it works well for me.
d, If the user did not provide any command line, the program can also just run by input the references manually.
e, seeing the output, after created the super calss the toString method is working well.

(5) what possible improvements could be done if you were to do it again or have extra time available. 
Like I said before, I did not finish the assignment due to hash map part, if I have more time I will change the seraching method with my hash map. Also, for the
command line, I should use 1 or 2 file names as input and output file. which I can check the args[0] as input file, args[1] as output file. This is not that hard.
