package at.natanb.acme.Model.Domain;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class S3Util {
    private static final String NOMEBUCKET = "acmenatanborges";

    public static void excluirObjeto(String nomeark) {
        System.out.println(nomeark);
        try {
            AmazonS3 s3 = configurar();
            s3.deleteObject(NOMEBUCKET, "imagens/" + nomeark);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());;
        }
    }

    public static void downloadObjeto(String fotoUrl) {
        System.out.println(fotoUrl);
        AmazonS3 s3 = configurar();
        S3Object s3Object = s3.getObject(NOMEBUCKET, "imagens/"+fotoUrl);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        System.out.println(s3Object.getBucketName());
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File("C:\\Users\\natan\\Downloads\\"+fotoUrl+".png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    public static void uploadObjeto(File file,String nomeark) {
        AmazonS3 s3 = configurar();

        try {
            s3.putObject(NOMEBUCKET,
                    "imagens/"+ nomeark,
                    file);
            System.out.println("Upload completo");
        }
        catch (AmazonS3Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



    public static AmazonS3 configurar() {
        AWSCredentials credentials = new BasicAWSCredentials(
                "Acess Key",
                "+Secret Key");

        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.SA_EAST_1)
                .build();
        return s3;
    }

}
