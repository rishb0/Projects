# status:complete
# version = 1.0
# author : rishabh soni
# date: 20 may 2024
# this is a  imaage to pdf maker,, you can make pdf from multiple  images  

from PIL import Image
import os
from pypdf import PdfWriter
from pypdf import PdfReader
from io import BytesIO
   
def menu():
    
    while True:
        messge=f'''.....Image to PDF tool by RS .....
                   Current directory ="{os.getcwd()}"
        Press:
        1)list directory
        2)change directory
        3)convert Images to PDF
        4)exit
        >'''
        choice = int(input(messge))
        if choice not in [1,2,3,4]:
            print("Invalid choice")
        elif choice==1:
            dirs=os.listdir()
            for index,value in enumerate(dirs):
                print(f'{index}={value}')
        elif choice==2:
            print(f"{os.getcwd()}\\")
            file_list=os.listdir()
            for index,file in enumerate(file_list,1):
                print(index,")",file)  
            
            dirnumber=int(input("  Change directory to:"))
            dirname=file_list[dirnumber-1]
            os.chdir(dirname)
        elif choice==3:
            imgToPDF()
        elif choice ==4:
            break

def imgToPDF():
    file_list=os.listdir()
    for index,file in enumerate(file_list,1):
        print(index,")",file)    

    img_number=list(map(int,input('''
                    Enter the images you want to include(seperat by space):''').split()))
    
    writer=PdfWriter()

    for i in img_number:
        i=i-1
        img_path=file_list[i]
        
        img =Image.open(img_path)
        if img.mode in ("RGBA",'P'):
            img=img.convert("RGB")
        
        img_buffer = BytesIO()
        img.save(img_buffer, format="PDF", resolution=100.0)
        img_buffer.seek(0)
        
        pdf = PdfReader(img_buffer)
        writer.append(pdf)
        
    output_file="combiledPDF.pdf"

    with open(output_file,'wb') as file:
        writer.write(file)

    print(f"\nAll images are convert to PDF successfully: {output_file}")

def main():
    menu()
    # print("\n...........Thank You Rinku Mera Bhai for using this program.......")


if __name__== "__main__":
    main()

