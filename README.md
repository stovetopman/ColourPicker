# Colour Tool

## A Brief Description

This application is intended but not limited to be used as an aide to those
interested in the visual arts. It will *take a colour in its hex code form*, and *produce a palette of 
visually appealing colours*, along with the hex code. Users will also be able to *randomly generate*
a colour palette, and be able to *save or favourite generated palettes and colours*.

As someone with a visual impairment, I understand the frustration people go through
in the pursuit of visual design or creation only to realize their genetic inability to process
colours to their fullest extent. Not knowing which colour I'm working with, nor knowing which colours would
go together with it, I struggle frequently with finding the right shades and hues. With this personal experience in mind, I hope to offer a method
to overcome these difficulties for those who share similar troubles as I do. 





User Stories:
- As a user, I want to be able to view my saved palettes and colours
- As a user, I want to be able to add to my saved palettes and colours
- As a user, I want to be able to remove items from my saved palettes and colours
- As a user, I want to be able to edit colours from my saved palettes and colours
- As a user, I want to be able to add the complementary colour of a colour in my palette
- As a user, I want to be able to add the monochromatic colours of a colour in my palette
- As a user, I want to have the option of saving my selected colours to file, and be reminded of it before exiting
- As a user, I want to have the option of loading a list of saved colours from file



## Phase 4: Task 2
Tue Apr 11 17:44:21 PDT 2023  
Loaded Palette to File.  
Tue Apr 11 17:44:21 PDT 2023  
Colour [4,77,88] Added to Palette.  
Tue Apr 11 17:44:25 PDT 2023  
Colour [3,3,3] Added to Palette.  
Tue Apr 11 17:44:30 PDT 2023  
Colour 0 [4, 77, 88] edited to Colour [1,3,4]  
Tue Apr 11 17:44:32 PDT 2023  
Complementary Colour Added to Palette:  
Tue Apr 11 17:44:32 PDT 2023  
Colour [254,252,251] Added to Palette.  
Tue Apr 11 17:44:34 PDT 2023  
Monochromatic Colours Added to Palette:  
Tue Apr 11 17:44:34 PDT 2023  
Colour [0,1,1] Added to Palette.  
Tue Apr 11 17:44:34 PDT 2023  
Colour [0,2,2] Added to Palette.  
Tue Apr 11 17:44:34 PDT 2023  
Colour [1,3,4] Added to Palette.  
Tue Apr 11 17:44:34 PDT 2023  
Colour [1,3,5] Added to Palette.  
Tue Apr 11 17:44:34 PDT 2023  
Colour [1,4,6] Added to Palette.  
Tue Apr 11 17:44:37 PDT 2023  
Saved Palette to File.   

## Phase 4: Task 3  
After going over my code, I can see some complications in code structure. Specifically, some large methods   
attempt to accomplish a set of tasks that could be spread across to multiple methods, and violates the Single Responsibility Principle.  
In some cases, I have already done so, such as in the initializeGraphics(), where the sequence of buttons being added are   
separated into its own method. The same thing is true for colourOrDisplaySet(), where it sets individuals panels   
on the overall frame. To improve the code structure, the same thing could be done for the main windowframe as well,   
which would further simplify and clarify the structure and intent of each method. This would benefit readability and   
the ease of future changes greatly. Another potential way to refactor would be to get rid of duplicate and repetitive code.   
For example, the sequence of buttons being added looks very repetitive, and is an opportunity to shorten the code.   
