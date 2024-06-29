# status = incompelete
# this is a gui calculator for normal use

from tkinter import *
root=Tk()

# b1 button's action
def b1_action():
    l2.config(text='button clickedd')

# adding title
root.title('Calculator')

#setting size of window
root.geometry('400x500')

#adding lable
l1=Label(root,text='Wecome to RS Calculator')
l1.pack(pady=10)

l2=Label(root,text='anser here')
l2.pack(pady=30)


#adding button
b1=Button(text="Click Here",command=b1_action)
b1.pack(pady=10)

root.mainloop()
