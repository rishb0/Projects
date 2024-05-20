#status: completed but it take too much time 
# version=1.1
# what's new= fixed the bug, before it checks for only n lenght passwords now it check for all lenght of passwordd till n
# this proram is made by rishabh soni 
# this will help us to remove the password from pdf files incase if we forget them
# also it will tell the password of the file
import itertools
import string
from PyPDF2 import PdfReader , PdfWriter

def pdfDecryptor(pdfName,password):
    with open(pdfName,'rb') as file:
        reader=PdfReader(file)
        
        if reader.is_encrypted:
            
            if reader.decrypt(password):
                
                writer=PdfWriter()
                
                for page in reader.pages:
                    writer.add_page(page)
                    
                with open('decrypted_pdf.pdf','wb') as outputFile:
                    writer.write(outputFile)
                print(f'the decrypted file is saved as "decrypted_pdf.pdf" ')
                return 1 
            else :
                return 0
            

        else :
            print("pdf is not encrypted")

    
def generate_passwords(n):
    # Define the character set
    characters = string.ascii_letters + string.digits + string.punctuation
    
    # Generate all combinations of characters up to length n
    for length in range(1, n + 1):
        all_combinations = itertools.product(characters, repeat=length)

    # Convert each combination into a string and yield it
        for combination in all_combinations:
            yield ''.join(combination)

def PDFPasswordRecovery(pdfName,level=16):
    
    crackedPassword=''
    isCracked=0
        
    tempPasswords= generate_passwords(level)
    
    for password in tempPasswords:
        isCracked=pdfDecryptor(pdfName,password)
        if isCracked==1:
            crackedPassword=password
            break
    
    print(f'the password of the pdf file is "{crackedPassword}" ')
        
def main():
    pdf=input('enter pdf name to remove password:')
    PDFPasswordRecovery(pdf,2)
    
if __name__=='__main__':
    main()
    
      
