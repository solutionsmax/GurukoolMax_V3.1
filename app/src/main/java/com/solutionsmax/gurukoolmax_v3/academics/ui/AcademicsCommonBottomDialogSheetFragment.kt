package com.solutionsmax.gurukoolmax_v3.academics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.data.AcademicsBottomSheetItem
import com.solutionsmax.gurukoolmax_v3.databinding.BottomSheetDialogListBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.ACADEMIC_PROJECT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.CCE_SCHOLASTIC
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.CURRICULUM_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.EXAMINATION_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.KNOWLEDGE_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.CurriculumManagement.COURSE_SYLLABUS_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.CurriculumManagement.REVIEW_LEARNING_SESSIONS
import com.solutionsmax.gurukoolmax_v3.operations.data.CurriculumManagement.SCHEDULE_ONLINE_LESSON_PLAN_SESSION
import com.solutionsmax.gurukoolmax_v3.operations.data.CurriculumManagement.SETUP_LEARNING_SESSIONS
import com.solutionsmax.gurukoolmax_v3.operations.data.CurriculumManagement.SUBJECT_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.ExaminationManagement.CONFIGURE_EXAM_TYPE
import com.solutionsmax.gurukoolmax_v3.operations.data.ExaminationManagement.EXAMINATION_REVIEW
import com.solutionsmax.gurukoolmax_v3.operations.data.ExaminationManagement.SCHEDULE_EXAMINATION
import com.solutionsmax.gurukoolmax_v3.operations.data.KnowledgeManagement.REPOSITORY_LIST
import com.solutionsmax.gurukoolmax_v3.operations.data.KnowledgeManagement.REPOSITORY_SEARCH
import com.solutionsmax.gurukoolmax_v3.operations.data.Project.PROJECT_LIST
import com.solutionsmax.gurukoolmax_v3.operations.data.Project.SEARCH
import com.solutionsmax.gurukoolmax_v3.operations.data.Project.SUBMISSION_SETUP
import com.solutionsmax.gurukoolmax_v3.operations.data.Scholastic.ACTIVITIES
import com.solutionsmax.gurukoolmax_v3.operations.data.Scholastic.GRADE_SYSTEM
import com.solutionsmax.gurukoolmax_v3.operations.data.Scholastic.PERFORMANCE
import com.solutionsmax.gurukoolmax_v3.operations.data.Scholastic.RECORDING

class AcademicsCommonBottomDialogSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDialogListBinding

    private var academicOptions = "-1"
    private var academicMenuOptions = emptyList<AcademicsBottomSheetItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDialogListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        academicOptions = requireArguments().getString("academicItem", "-1")


        when (academicOptions) {
            CURRICULUM_MANAGEMENT -> {
                academicMenuOptions = listOf(
                    AcademicsBottomSheetItem(SUBJECT_MANAGEMENT),
                    AcademicsBottomSheetItem(COURSE_SYLLABUS_MANAGEMENT),
                    AcademicsBottomSheetItem(SETUP_LEARNING_SESSIONS),
                    AcademicsBottomSheetItem(REVIEW_LEARNING_SESSIONS),
                    AcademicsBottomSheetItem(SCHEDULE_ONLINE_LESSON_PLAN_SESSION)
                )
            }
            CCE_SCHOLASTIC -> {
                academicMenuOptions = listOf(
                    AcademicsBottomSheetItem(GRADE_SYSTEM),
                    AcademicsBottomSheetItem(RECORDING),
                    AcademicsBottomSheetItem(ACTIVITIES),
                    AcademicsBottomSheetItem(PERFORMANCE)
                )
            }
            ACADEMIC_PROJECT -> {
                academicMenuOptions = listOf(
                    AcademicsBottomSheetItem(SUBMISSION_SETUP),
                    AcademicsBottomSheetItem(PROJECT_LIST),
                    AcademicsBottomSheetItem(SEARCH)
                )
            }
            KNOWLEDGE_MANAGEMENT -> {
                academicMenuOptions = listOf(
                    AcademicsBottomSheetItem(REPOSITORY_LIST),
                    AcademicsBottomSheetItem(REPOSITORY_SEARCH)
                )
            }
            EXAMINATION_MANAGEMENT -> {
                academicMenuOptions = listOf(
                    AcademicsBottomSheetItem(CONFIGURE_EXAM_TYPE),
                    AcademicsBottomSheetItem(SCHEDULE_EXAMINATION),
                    AcademicsBottomSheetItem(EXAMINATION_REVIEW)
                )
            }
        }

        with(binding.bottomSheetList) {
            val navHostFragment =
                requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            layoutManager = LinearLayoutManager(requireContext())
            val bundle = bundleOf("id" to -1)
            adapter = AcademicsCommonBottomDialogAdapter(
                academicMenuOptions,
                AcademicsCommonBottomDialogAdapter.OnItemClick {
                    when (it) {
                        GRADE_SYSTEM -> navController
                            .navigate(R.id.scholasticGradeSystemListFragment)
                        RECORDING -> navController.navigate(R.id.cceScholasticListFragment)
                        ACTIVITIES -> navController.navigate(R.id.cceScholasticActivityListFragment)
                        PERFORMANCE -> navController.navigate(R.id.academicsPerformanceSearchFragment)

                        SUBJECT_MANAGEMENT -> navController.navigate(R.id.subjectManagementListFragment)
                        COURSE_SYLLABUS_MANAGEMENT -> navController.navigate(R.id.curriculumSetupListFragment)
                        SETUP_LEARNING_SESSIONS -> navController.navigate(R.id.setupLearningSessionListFragment)
                        REVIEW_LEARNING_SESSIONS -> navController.navigate(R.id.reviewLearningSessionListFragment)
                        SCHEDULE_ONLINE_LESSON_PLAN_SESSION -> navController.navigate(R.id.onlineSessionLessonsPlanListFragment)

                        SUBMISSION_SETUP -> navController.navigate(
                            R.id.projectSubmissionInfoFragment,
                            bundle
                        )
                        PROJECT_LIST -> navController.navigate(R.id.projectsRepositoryListFragment)
                        SEARCH -> navController.navigate(R.id.projectsRepositorySearchFragment)

                        REPOSITORY_LIST -> navController.navigate(R.id.KMRepositoryListFragment)
                        REPOSITORY_SEARCH -> navController.navigate(R.id.KMSearchRepositoryFragment)

                        CONFIGURE_EXAM_TYPE -> navController.navigate(R.id.examScheduleListFragment)
                        SCHEDULE_EXAMINATION -> navController.navigate(R.id.examinationSetupListFragment)
                        EXAMINATION_REVIEW -> navController.navigate(R.id.examinationResultsListFragment)
                    }
                })
        }
    }
}