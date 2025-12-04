This class is used to issue draw calls to a Canvas using a buffer.

Each call pushes the necessary parameters onto the buffer where they will later be rendered onto the image of the Canvas node by the rendering thread at the end of a pulse.

A Canvas only contains one GraphicsContext, and only one buffer. 
