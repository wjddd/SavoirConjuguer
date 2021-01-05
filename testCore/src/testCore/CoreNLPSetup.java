package testCore;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;

import java.io.*;
import java.util.*;

public class CoreNLPSetup {
    private static CoreNLPSetup instance = new CoreNLPSetup();
    private StanfordCoreNLP pipeline;
    private CoreNLPSetup() {
        Properties props = new Properties();
        // définition du pipeline
        props.setProperty("annotators", "tokenize, ssplit, parse, lemma");
        // paramétrage pour le français
        props.setProperty("props", "StanfordCoreNLP-french.properties");
        props.setProperty("tokenize.language","French");
        props.setProperty("parse.model","edu/stanford/nlp/models/lexparser/frenchFactored.ser.gz");
        props.setProperty("pos.model","edu/stanford/nlp/models/postagger/french/french.tagger");
        props.setProperty("depparse.model","edu/stanford/nlp/models/parser/nndep/UD_French.gz");
        props.setProperty("depparse.language","French");
        props.setProperty("tokenize.verbose","false"); // True = affiche les tokens
        RedwoodConfiguration.current().clear().apply();
        pipeline = new StanfordCoreNLP(props);
    };
    public static CoreNLPSetup getInstance(){
        return instance;
    }
    public StanfordCoreNLP getPipeline(){
        return pipeline;
    }
}
