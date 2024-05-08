# Write a python program to translate a message into secret code language. Use the rules below to translate normal English into secret code language

# Coding:
# if the word contains atleast 3 characters, remove the first letter and append it at the end
#   now append three random characters at the starting and the end
# else:
#   simply reverse the string

# Decoding:
# if the word contains less than 3 characters, reverse it
# else:
#   remove 3 random characters from start and end. Now remove the last letter and append it to the beginning

# Your program should ask whether you want to code or decode

import random
import string


def encode(input_message):
    words_list=input_message.split()
    for i in range(len(words_list)):
       if len(words_list[i]) <= 3:
         words_list[i]=words_list[i][::-1]
       else:
          r1=random.choice(string.ascii_letters)
          r2=random.choice(string.ascii_letters)
          r3=random.choice(string.ascii_letters)
          r4=random.choice(string.ascii_letters)
          r5=random.choice(string.ascii_letters)
          r6=random.choice(string.ascii_letters)
          words_list[i]=r1+r2+r3+words_list[i][::-1]+r4+r5+r6
    
    input_message=""
    for i in words_list:
      input_message+=i
      input_message+=" "
 
    return input_message
  

def decode(input_message):
    words_list=input_message.split()
    for i in range(len(words_list)):
       if len(words_list[i]) <= 3:
         words_list[i]=words_list[i][::-1]
       else:
         words_list[i]=words_list[i][3:-3]
         words_list[i]=words_list[i][::-1]
         
    input_message=""
    for i in words_list:
      input_message+=i
      input_message+=" "
 
    return input_message
  
input_message=input("Enter the message \n")
choice = int(input("press \n1) encode \n2) decode"))

if choice == 1:
  message=encode(input_message)
  print(message)
if choice == 2:
  message=decode(input_message)
  print(message)
