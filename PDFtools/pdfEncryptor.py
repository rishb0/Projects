# status:complete
# version = 1.0.1
# authpr : rishabh soni
# date: 8 may 2024
# this is a pdf file encryptor ,we can add password to a pdf file by this tool 


from  PyPDF2 import PdfWriter , PdfReader
class myPDFLocker:
    def pdfEncryptor(self,pdfName,password):
        with open(pdfName,'rb') as file:   # opening pdf file in read binary mode
            reader=PdfReader(file)      #making PdfFieReader class object
            writer=PdfWriter()      #making PdfFieWriter class object
            
            #reading all pages from file and addding to writer 
            for page in reader.pages:
                writer.add_page(page)
            
            #encrypt the pdf with password given
            writer.encrypt(password)
            
            # writing the encryptedd pdf to new file
            with open('encryptedPDF_PDF.pdf','wb') as Outputfile:   #opening ile in write binary mode , if not present it is created
                writer.write(Outputfile)
            

def main():
    pdftool=myPDFLocker()
    pdf=input('enter the pdf path to encrypt:')
    password=input('enter the password :')
    pdftool.pdfEncryptor(pdf,password)
    
if __name__ == '__main__':
    main()
