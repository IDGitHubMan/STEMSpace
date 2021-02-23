#All the imports!
import numpy as np
import random
import simpleaudio as sa
from tkinter import*
from matplotlib.backends.backend_tkagg import (FigureCanvasTkAgg, NavigationToolbar2Tk)
from matplotlib.backend_bases import key_press_handler
from matplotlib.figure import Figure

#The actual app
class STEMSpaceApp(Tk):
##    class NodeNet(self):
##        def __init__(self,canvas):
##            self.nodeStore = []
##            for i in range(41):
##                self.node = [random.uniform(0,canvas.winfo_width,random),random.uniform(0,canvas.winfo_height),random.uniform(-2,2),random.uniform(-2,2),random.uniform(0,255),random.uniform(0,255),random.uniform(0,255)]
##                self.nodeStore.append(node)
##        def update(self):
##            for j in range(41):
##                canvas.create_oval(
##                self.node[j][0] += self.node[j][2]
##                self.node[j][1]+=self.node[j][3]
##                if self.node[j][0] >canvas.winfo_width:
##                    self.node[j][0] = 0
##                if self.node[j][0] <0:
##                    self.node[j][0] = canvas.winfo_width
##                if self.node[j][1] >canvas.winfo_height:
##                    self.node[j][1] = 0
##                if self.node[j][1] <0:
##                    self.node[j][1] = canvas.winfo_height
##        def draw
    def __init__(self):
        Tk.__init__(self)
        self.attributes("-fullscreen",True)
        self.resizable(0,0)
        self._frame = None
        self.switch_frame(Home)

    def switch_frame(self, frame_class):
        """Destroys current frame and replaces it with a new one."""
        new_frame = frame_class(self)
        if self._frame is not None:
            self._frame.destroy()
        self._frame = new_frame
        self._frame.grid(row=0,column=0)

#The Home Page
class Home(Frame):
    def __init__(self,master):
        Frame.__init__(self,master,width=master.winfo_width(),height=master.winfo_height())
        bg = Canvas(self,width=self.winfo_width(),height=self.winfo_height(),background="navy")
        bg.place(x=0,y=0)
        title = Label(self, text="STEMSpace").grid()
        sciButton = Button(self, text="SciSpace",command=lambda: master.switch_frame(SciSpace)).grid()
        techButton = Button(self,text="TechSpace",command=lambda: master.switch_frame(TechSpace)).grid()
        mathButton = Button(self,text="MathSpace",command=lambda: master.switch_frame(MathSpace)).grid()
                

class SciSpace(Frame):
    def __init__(self,master):
        Frame.__init__(self,master)
        title = Label(self, text="SciSpace")
        title.grid()

class TechSpace(Frame):
    def __init__(self,master):
        Frame.__init__(self,master)
        title = Label(self, text="TechSpace")
        title.grid()

class MathSpace(Frame):
    def __init__(self,master):
        Frame.__init__(self,master)
        title = Label(self, text="MathSpace")
        title.grid()
        
if __name__ == "__main__":
    app = STEMSpaceApp()
    app.mainloop()
