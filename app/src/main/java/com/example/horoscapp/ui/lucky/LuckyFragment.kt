package com.example.horoscapp.ui.lucky

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LuckyFragment : Fragment() {
    @Inject
    lateinit var randomCardsProvider: RandomCardsProvider

    private val viewModel by viewModels<LuckyViewModel>()
    private lateinit var luckyBinding: FragmentLuckyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val rotationAnimation: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
//        luckyBinding.tvLuckyInfo.startAnimation(rotationAnimation)

        luckyBinding.viewFrontContainer.viewFront.setOnClickListener {
            prepareCard()
            flipCard()
        }
    }

    private fun flipCard() {
        try {
            // Visibility
            luckyBinding.viewFrontContainer.viewFront.isVisible = true

            // 3D Affect
            val scale = requireContext().resources.displayMetrics.density
            val cameraDist = 8000 * scale
            luckyBinding.viewFrontContainer.viewFront.cameraDistance = cameraDist
            luckyBinding.viewBackContainer.viewBack.cameraDistance = cameraDist

            // Recovery Animation FLIP OUT
            val flipOutAnimatorSet = AnimatorInflater.loadAnimator(
                requireContext(),
                R.animator.flip_out
            ) as AnimatorSet
            // Applying the animation
            flipOutAnimatorSet.setTarget(luckyBinding.viewBackContainer.viewBack)

            // Recovery Animation FLIP IN
            val flipInAnimatorSet = AnimatorInflater.loadAnimator(
                requireContext(),
                R.animator.flip_in
            ) as AnimatorSet
            // Applying the animation
            flipInAnimatorSet.setTarget(luckyBinding.viewFrontContainer.viewFront)

//            flipInAnimatorSet.doOnStart {
//                luckyBinding.tvLuckyInfo.animate().alpha(1f).duration = 1500
//            }

            // initializing the animations
            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()

            flipInAnimatorSet.doOnEnd {
                luckyBinding.viewBackContainer.viewBack.isVisible = false
                luckyBinding.tvLuckyInfo.animate().alpha(1f).duration = 750
            }

        } catch (e: java.lang.Exception) {
            Log.e("Error LuckyFrag", "There was an error at the flipCard function: $e")
        }
    }

    private fun prepareCard() {
        val luckData = randomCardsProvider.getLucky()
        luckyBinding.viewFrontContainer.ivLuckyCard.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                luckData.image
            )
        )
        luckyBinding.tvLuckyInfo.text = getString(luckData.text)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.luckyBinding = FragmentLuckyBinding.inflate(layoutInflater)
        return this.luckyBinding.root
    }
}