package termproject01;


import javax.imageio.IIOImage;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class GIFA {

	
	GIFA(){
		
	}
	
    public void main(String Fusetime,String[] args) {
//        String[] inputImagePaths = {
//            "src\\output1.png",
//            "src\\output2.png",
//            "src\\output3.png"  
//        };
        
        List<String> inputImagePathsList = new ArrayList<>();

        for (int i = 1; i <= mainWindow.numTabsTempForGIFA-1; i++) {
            String imagePath = "src\\output" + i + ".png";
            inputImagePathsList.add(imagePath);
        }

        // Convert the list to an array if needed
        String[] inputImagePaths = inputImagePathsList.toArray(new String[0]);

        // Now, inputImagePaths array contains paths like {"src\\output1.png", "src\\output2.png", ..., "src\\output(num).png"}

        String outputGifPath = "src\\outputGifa.gif"; // 完整的輸出路徑

        try {
            createAndWriteGifAnimation(inputImagePaths, outputGifPath, Fusetime);
            System.out.println("GIF animation created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createAndWriteGifAnimation(String[] imagePaths, String outputGifPath,String Fusetime) throws Exception {
        ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
        ImageWriter gifWriter = ImageIO.getImageWritersByFormatName("gif").next();
        
        File outputFile = new File(outputGifPath);
        gifWriter.setOutput(ImageIO.createImageOutputStream(outputFile));
        
        IIOMetadata metadata = gifWriter.getDefaultImageMetadata(typeSpecifier, null);
        String format = metadata.getNativeMetadataFormatName();
        IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(format);
        IIOMetadataNode graphicsControlExtension = getNode(root, "GraphicControlExtension");
        graphicsControlExtension.setAttribute("disposalMethod", "none");
        graphicsControlExtension.setAttribute("transparentColorFlag", "false");
        graphicsControlExtension.setAttribute("delayTime",Fusetime);
        graphicsControlExtension.setAttribute("transparentColorIndex", "0");

        IIOMetadataNode comments = getNode(root, "CommentExtensions");
        comments.setAttribute("CommentExtension", "Created with Java");

        metadata.setFromTree(format, root);

        gifWriter.setOutput(ImageIO.createImageOutputStream(outputFile));
        gifWriter.prepareWriteSequence(null);
        
        for (String imagePath : imagePaths) {
            BufferedImage image = ImageIO.read(new File(imagePath));
            if (image != null) {
                IIOImage frame = new IIOImage(image, null, metadata);
                gifWriter.writeToSequence(frame, null);
            } else {
                System.err.println("Failed to read image: " + imagePath);
            }
        }
        
        gifWriter.endWriteSequence();
    }

    public static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
        int nNodes = rootNode.getLength();
        for (int i = 0; i < nNodes; i++) {
            if (rootNode.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0) {
                return ((IIOMetadataNode) rootNode.item(i));
            }
        }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        rootNode.appendChild(node);
        return node;
    }
}
