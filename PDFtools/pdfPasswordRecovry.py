#status: completed but not tested 
# version=1.1
# what's new= fixed the bug, before it checks for only n lenght passwords now it check for all lenght of passwordd till n
# this proram is made by rishabh soni 
# this will help us to remove the password from pdf files incase if we forget them
# also it will tell the password of the file
import itertools
import string
from pdfDecryptor import myPDFUnlocker
class myCracker:
    
    def generate_passwords(self,n):
        # Define the character set
        characters = string.ascii_letters + string.digits + string.punctuation
        
        # Generate all combinations of characters up to length n
        for length in range(1, n + 1):
            all_combinations = itertools.product(characters, repeat=length)

        # Convert each combination into a string and yield it
            for combination in all_combinations:
                yield ''.join(combination)

    def PDFPasswordRecovery(self,pdfName,level=16):
        
        cracker=myPDFUnlocker()
        crackedPassword=''
        isCracked=0
            
        tempPasswords= self.generate_passwords(level)
        
        for password in tempPasswords:
            isCracked=cracker.pdfDecryptor(pdfName,password)
            if isCracked==1:
                crackedPassword=password
                break
        
        print(f'the password of the pdf file is "{crackedPassword}" ')
        
def main():
    pdf=input('enter pdf name to remove password:')
    pdftool=myCracker()
    pdftool.PDFPasswordRecovery(pdf,2)
    
if __name__=='__main__':
    main()
    
      
