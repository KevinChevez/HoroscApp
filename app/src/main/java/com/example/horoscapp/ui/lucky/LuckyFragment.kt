package com.example.horoscapp.ui.lucky

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class LuckyFragment : Fragment() {
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
        val image = when (Random.nextInt(0, 5)) {
            0 -> R.drawable.card_fool
            1 -> R.drawable.card_moon
            2 -> R.drawable.card_hermit
            3 -> R.drawable.card_star
            4 -> R.drawable.card_sun
            5 -> R.drawable.card_sword
            else -> R.drawable.card_reverse
        }
        luckyBinding.viewFrontContainer.ivLuckyCard.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                image
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.luckyBinding = FragmentLuckyBinding.inflate(layoutInflater)
        return this.luckyBinding.root
    }
}