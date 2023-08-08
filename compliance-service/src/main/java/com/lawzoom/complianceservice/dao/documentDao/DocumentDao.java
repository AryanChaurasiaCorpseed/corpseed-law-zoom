package com.lawzoom.complianceservice.dao.documentDao;

import com.lawzoom.complianceservice.model.complianceModel.Compliance;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.model.documentModel.Document;

import java.util.List;

public interface DocumentDao {
    List<Document> findAllDocumentsByCompliance(Compliance compliance);

    Document saveDocument(Document document);

    Document findDocumentById(Long id);

    Document updateDocument(Document document);

    Document findDocumentByComplianceAndId(Compliance compliance, Long id);

    boolean deleteDocument(Document document);

    Document findDocumentByComplianceTaskAndId(ComplianceTask complianceTask, Long id);

    List<Document> findAllDocumentsByComplianceTask(ComplianceTask complianceTask);

    List<Document> findAllDocumentsByComplianceSubTask(ComplianceSubTask complianceSubTask);

    Document findDocumentByComplianceSubTaskAndId(ComplianceSubTask complianceSubTask, Long id);
}
