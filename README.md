# Multimedia-Systems-2022
 
## Option 1.
Create a desktop media player with typical player functionality: play/pause, stop, forward-reverse, playback slider, time display buttons. It should play the most basic audio and video formats, as well as streaming media.
## To perform the laboratory work, the following was used:
1) The programming language is java
2) Java framework [vclj](https://www.videolan.org/vlc/libvlc.html) – for integrating the media player into your own application.
3) Java library [Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) – for creating a desktop application with a graphical interface.
## Progress:
An application with a graphical interface was developed using the Swing library. Added two media player control panels that disappear when the user stops moving the mouse or pressing buttons. <br />
The first control panel is located at the top of the application. Buttons for opening a file, playing video/audio from a direct link to a file on the server, and a button that opens a window with a history of open files and links have been added to it. Also, a button was added to the first panel, which fixes the panels (that is, the panels will stop disappearing). <br />
The second control panel is located at the bottom of the application. It has pause/start, stop, back, forward buttons, a video/audio rewind slider, a playback volume slider, and a text field displaying the current playback time and total time.
Special keys were added to each button. Pause/start - SPACE, stop - S, back - LEFT ARROW, forward - RIGHT ARROW, volume up - TOP ARROW, volume down - DOWN ARROW, pin panels - P, open file - F, play by link - U, open history – H, exit full-screen mode – ESC. Also, when you press the right mouse button on the player screen, the pause/start function will be activated. Double-clicking the application will switch to or exit full-screen mode.<br />
The created and configured media player component from the vclj framework was added to the central part of the application. A field has been added next to it, which stores the name of the open file/link. This field disappears together with the panels.
## The player can play the following video formats:
MPEG-1/2, DivX® (1/2/3/4/5/6), MPEG-4 ASP, XviD, 3ivX D4, H.261, H.263 / H.263i, H.264 / MPEG- 4 AVC, Cinepak, Theora, Dirac / VC-2, MJPEG (A/B), WMV 1/2, WMV 3 / WMV-9 / VC-1, Sorenson 1/3, DV, On2 VP3/VP5/VP6, Indeo Video v3 (IV32), Real Video (1/2/3/4).
## And audio formats:
MPEG Layer 1/2, MP3 - MPEG Layer 3, AAC - MPEG-4 part3, Vorbis, AC3 - A/52, E-AC-3, MLP / TrueHD>3, DTS, WMA 1/2, WMA 3, FLAC , ALAC, Speex, Musepack / MPC, ATRAC 3, Wavpack, Mod, TrueAudio, APE, Real Audio, Alaw/µlaw, AMR (3GPP), MIDI, LPCM, ADPCM, QCELP, DV Audio, QDM2/QDMC, MACE. <br />
Information taken from [official site](https://www.videolan.org/vlc/features.html).
### The player can also play streaming video and audio. For playback, you need to specify a direct link to a media file or stream on the server.
