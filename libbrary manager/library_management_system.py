# this is a CWH exersice 
# this is a library management system , and it is ccomplete
# just i need to add some nchancememnts in it , like , thee line that the taks selected is usccessfully done or fiualed 
# i will updat that messge in the net commit 
# for now it is working and i had  checked  it   
import json
import os
class library:
  def __init__(self,lib_name):
    self.books=[]
    self.number_of_books=0
    self.library_name=lib_name
    if os.path.exists(lib_name):
      self.fetchRecord()
    self.updateRecord()
    
  def updateRecord(self):
    with open(self.library_name,'w') as file:
      data={'library_name':self.library_name,"number_of_books":self.number_of_books,'books':self.books}
      json.dump(data,file)
    # print("Record updated succcessfully..")

  def fetchRecord(self):
    with open(self.library_name,'r') as file:
      data=json.load(file)
      self.books=data['books']
      self.number_of_books=data['number_of_books']
      self.library_name=data['library_name']
    # print("Record fetched!")
  
  #cheks that the record of names of bookks is correct with the total number of books added are equivalent
  def isRecordCorrect(self):
    self.fetchRecord()
    return True if self.number_of_books == len(self.books) else False

  def addBook(self,book_name):
    book_name.capitalize()
    self.fetchRecord()
    if self.isBookExist(book_name):
      print("Book already exist")
    else:
      self.books.append(book_name)
      self.number_of_books+=1
    self.updateRecord()
    
  def showAllBooks(self):
    self.fetchRecord()
    if self.number_of_books==0:
      print("No books to show")
    else :
      print(f"\nAll book of {self.library_name} library are :")
      for id , book_name in enumerate(self.books):
        print(f'{id} {book_name}')

  def removeBook(self,bookname):
    self.fetchRecord()
    if self.isBookExist(bookname):
      self.books.remove(bookname)
      self.number_of_books-=1
    else :
      print('the book to be removed is not found')
    self.updateRecord()
  
  def isBookExist(self,bookname):
    self.fetchRecord()
    if bookname in self.books:
      return True
    else :
      return False

  def editBookName(self,oldName,newName):
    self.fetchRecord()
    if self.isBookExist(oldName):
      self.books[self.books.index(oldName)]=newName
    else : 
      print('the book to edit does not exist')
    self.updateRecord()

def user_interface():
  
  while True:
    choice=int(input("Welcome to LMS \npress 1)login \npress 2)exit program\n"))
    if choice not in [1,2]:
      print('invalid choice,please enter a valid choice..\n')
    if choice == 2:
      break
    if choice ==1:
      lib_name=input('enter the library name :')
      lib_name.capitalize()
      lib_obj=library(lib_name)
      while True:
        print("\nselect the task to perform:-")
        choice=int(input('1)Add book\n2)remove book\n3)show all books\n4)edit book name\n5)logout\n:'))
        if choice not in [1,2,3,4,5]:
          print('invalid choice')
        else:
          match choice:
            case 1:
              book_name=input("enter book to add :")
              lib_obj.addBook(book_name)
            case 2:
              book_name=input("enter book to remove :")
              lib_obj.removeBook(book_name)
            case 3:
              lib_obj.showAllBooks()
            case 4:
              old_name=input("enter old book name :")
              new_name=input("enter new book name :")
              lib_obj.editBookName(old_name,new_name)
            case 5:
              print("logging out...\n\n")
              break  
user_interface()
