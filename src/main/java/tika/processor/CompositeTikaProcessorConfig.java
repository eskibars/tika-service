package tika.processor;

import com.fasterxml.jackson.annotation.JsonView;
import common.JsonPropertyAccessView;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * The composite PDF processor configuration
 */
@Data
@Configuration
public class CompositeTikaProcessorConfig {
    // resize the image to lower or higher scale, min value 100, max value 900
    // lower values will make image processing to faster, at the cost of text extract quality (how
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.tesseract-ocr.resize:200}")
    private int ocrResize;

    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.tesseract-ocr.density:300}")
    private int ocrDensity;

    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.tesseract-ocr.filter:triangle}")
    private String ocrFilter;

    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.tesseract-ocr.depth:4}")
    private int ocrDepth;

    // the timeout value (s) when performing OCR over documents
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.tesseract-ocr.timeout:120}")
    private int ocrTimeout;

    // apply image processing techniques during documents conversion (using ImageMagick)
    // required to enable applying rotation (see below)
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.tesseract-ocr.enable-image-processing:false}")
    private boolean ocrEnableImageProcessing;


    // apply de-rotation of documents before processing
    // can be quite computationally expensive (runs as an external python script)
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.tesseract-ocr.apply-rotation:false}")
    private boolean ocrApplyRotation;

    // the language used in the OCR for corrections
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.tesseract-ocr.language:eng}")
    private String ocrLanguage;

    // whether to apply OCR only on the documents or also extract the embedded text (if present)
    // warn: note that applying 'OCR_AND_TEXT_EXTRACTION' the content can be duplicated
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.pdf-ocr-parser.ocr-only-strategy:true}")
    private boolean pdfOcrOnlyStrategy;

    // disabling should speed things up
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.pdf-ocr-parser.ocr-detect-angles:false}")
    private boolean pdfOcrDetectAngles;

    // DPI at which to process images, lower values will make it go faster at the cost of text extract quality
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.pdf-ocr-parser.ocr-dpi:300}")
    private int pdfOcrDPI;

    // apply OCR only when trying to extract text from previously parsed document (w/o OCR)
    // that extracted characters were less than N
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.pdf-ocr-parser.min-doc-text-length:100}")
    private int pdfMinDocTextLength;

    // apply OCR only when trying to extract text from previously parsed document (w/o OCR)
    // that the read bytes were at least N
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.pdf-ocr-parser.min-doc-byte-size:10000}")
    private int pdfMinDocByteSize;

    // use a legacy parser for applying OCR for single-page PDF documents
    // (NB: when exporting single-page PDFs from LibreOffice that contain only one image,
    //   some additional clutter may be embedded in the PDF content)
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.parsers.use-legacy-ocr-parser-for-single-page-doc:false}")
    private boolean useLegacyOcrParserForSinglePageDocuments;

    // number of consumer for file batch processing
    @JsonView(JsonPropertyAccessView.Public.class)
    @Value("${tika.bulk.num-consumers:8}")
    private int batchNumConsumers;
}
