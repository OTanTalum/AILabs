import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Size;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

class EqualizeHist {
    public void run(String[] args) {
        String filename = args.length > 0 ? args[0] : "IMG.jpg";
        Mat src = Imgcodecs.imread(filename);
        if (src.empty()) {
            System.err.println("Cannot read image: " + filename);
            System.exit(0);
        }
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);


        Mat dst = new Mat();
        Mat nod = new Mat();
        Size size = new Size(500, 600);
        Imgproc.resize(src, src, size,0.4,0.4, Imgproc.INTER_AREA);

        HighGui.imshow( "Hist", new EqualizeHist().Hist(src) );
        Imgproc.equalizeHist( src, dst );

        HighGui.imshow( "Hist", new EqualizeHist().Hist(src) );
        Imgproc.equalizeHist( src, dst );
        HighGui.imshow( "Hist Demo", new EqualizeHist().Hist(dst) );
        Core.normalize(src, nod, 0, 255, Core.NORM_MINMAX);
        HighGui.imshow( "Hist nomal", new EqualizeHist().Hist(nod) );

        HighGui.imshow( "No image", nod );
        HighGui.imshow( "Source image", src );
        HighGui.imshow( "Equalized Image", dst );
        HighGui.waitKey(0);
        System.exit(0);
    }
    public Mat Hist(Mat src){

    List<Mat> bgrPlanes = new ArrayList<>();
    Core.split(src, bgrPlanes);
    int histSize = 256;
    float[] range = {0, 256}; //the upper boundary is exclusive
    MatOfFloat histRange = new MatOfFloat(range);
    boolean accumulate = false;

    Mat Hist = new Mat();
    Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), Hist, new MatOfInt(histSize), histRange, accumulate);
    int histW = 1024, histH = 400;
    int binW = (int) Math.round((double) histW / histSize);
    Mat histImage = new Mat( histH, histW, CvType.CV_8UC3, new Scalar( 0,0,0) );
    Core.normalize(Hist, Hist, 0, histImage.rows(), Core.NORM_MINMAX);

    float[] bHistData = new float[(int) (Hist.total() * Hist.channels())];
    Hist.get(0, 0, bHistData);

    for( int i = 1; i < histSize; i++ ) {
        Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(bHistData[i - 1])),
                new Point(binW * (i), histH - Math.round(bHistData[i])), new Scalar(255, 255, 255), 2);
    }
    return (histImage);
}
}
public class Main {
    public static void main(String[] args) {
        // Load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new EqualizeHist().run(args);
    }
}