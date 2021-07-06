package com.example.fragment_1_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.fragment_1_3.databinding.FragmentReceiverBinding

class ReceiverFragment : Fragment() {
    lateinit var binding: FragmentReceiverBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 값을 보내는 측 프래그먼트에서 "request" 라는 키로 값을 보내면 이 리스너 안의 코드가 실행된다.
        setFragmentResultListener("request") { key, bundle ->
            //let을 사용해서 꺼낸 값이 있을 때만 화면에 값을 세팅한다.
            //"request"는 요청 전체에 대한 키이고
            //"valueKey"는 요청에 담겨 있는 여러 개의 값 중 하나의 값을 가리키는 키
            bundle.getString("valueKey")?.let{
                binding.textView.setText(it)
            }
        }
    }
}