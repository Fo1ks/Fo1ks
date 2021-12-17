package com.example.anew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.anew.databinding.FragmentGreetingsBinding

class FragmentGreetings : Fragment() {
    lateinit var binding: FragmentGreetingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGreetingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    FragmentResponse().also {
                        it.arguments = Bundle().also {
                            it.putString("userId", binding.UserId.text.toString())
                            it.putString("title", binding.Title.text.toString())
                            it.putString("body", binding.Body.text.toString())
                        }
                    }
                )
                .addToBackStack(null)
                .commit()
        }
    }

}