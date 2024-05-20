# status:complete
# version = 1.0
# authpr : rishabh soni
# date: 8 may 2024
# this is a pdf file decryptor ,we can removepassword of a pdf file by this tool 


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

def main():
    
    pdf=input('enter the pdf path to decrypt:')
    password=input('enter the password :')
    pdfDecryptor(pdf,password)
    
    
if __name__ == "__main__":
    main()