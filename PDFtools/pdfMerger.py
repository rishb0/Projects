# this is a pdf merger tool
# it is now working  
# exercise 8 by cwh

from pypdf import PdfWriter
import os
# from datetime import datetime
class myPDFMerger:
    
    def mergePDFs(self):
        pdfList=self.inputPDFList()
        merger=PdfWriter()
        for pdf in pdfList:
            merger.append(pdf)
        # current_time=datetime.now()
        merger.write(f'merged_pdf.pdf')
        merger.close()
        mergedPDFPath=f'{os.getcwd()}\merged_pdf.pdf'
        print(f"merged pdf is saved at {mergedPDFPath}")
    
    def inputPDFList(self):
        pdfList=[]
        n=int(input("enter number of pdf  file to merge:"))
        for i in range(n):
            pdfName=input(f'enter {i+1} pdf file path:')
            pdfList.append(pdfName)
        return pdfList
        
def main():
    pdftool=myPDFMerger()
    
        
    pdftool.mergePDFs()
    
main()
