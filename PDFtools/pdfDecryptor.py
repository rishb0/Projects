# status:complete
# version = 1.0
# authpr : rishabh soni
# date: 8 may 2024
# this is a pdf file decryptor ,we can removepassword of a pdf file by this tool 


from PyPDF2 import PdfReader , PdfWriter

class myPDFUnlocker:
    def pdfDecryptor(self,pdfName,password):
        with open(pdfName,'rb') as file:
            reader=PdfReader(file)
            
            if reader.is_encrypted:
                
                if reader.decrypt(password):
                    
                    writer=PdfWriter()
                    
                    for page in reader.pages:
                        writer.add_page(page)
                        
                    with open('decrypted_pdf.pdf','wb') as outputFile:
                        writer.write(outputFile)
                        
                else :
                    return -1
                

            else :
                print("pdf is not encrypted")

def main():
    pdftool=myPDFUnlocker()
    pdf=input('enter the pdf path to decrypt:')
    password=input('enter the password :')
    pdftool.pdfDecryptor(pdf,password)
    
    
if __name__ == "__main__":
    main()