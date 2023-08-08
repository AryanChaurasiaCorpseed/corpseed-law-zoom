package com.lawzoom.complianceservice.dao.taskActionDao;

import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.model.taskActionModel.TaskAction;

import java.util.List;

public interface TaskActionDao {
    List<TaskAction> findTaskActionsByComplianceTask(ComplianceTask complianceTask);

    List<TaskAction> findTaskActionsByComplianceSubTask(ComplianceSubTask complianceSubTask);

    TaskAction saveTaskAction(TaskAction taskAction);

    TaskAction updateTaskAction(TaskAction taskAction);

    TaskAction findTaskActionByComplianceTaskAndId(ComplianceTask complianceTask, Long actionId);

    TaskAction findTaskActionByComplianceSubTaskAndId(ComplianceSubTask complianceSubTask, Long actionId);

    boolean deleteTaskAction(TaskAction taskAction);
}
