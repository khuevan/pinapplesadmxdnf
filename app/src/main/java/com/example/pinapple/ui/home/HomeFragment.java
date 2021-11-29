package com.example.pinapple.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.pinapple.R;
import com.example.pinapple.adapters.ImageAdapter;
import com.example.pinapple.databinding.FragmentHomeBinding;
import com.example.pinapple.models.DataImage;
import com.example.pinapple.service.ApiClient;
import com.example.pinapple.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView imgRecyclerview;
    ImageAdapter imgAdapter;
    List<DataImage.ImageModel> listImage = new ArrayList<>();
    DataImage dataImage;
//    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imgRecyclerview = root.findViewById(R.id.image_recyclerView);


        fetchImages();
        imgAdapter = new ImageAdapter(listImage);

        imgRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        imgRecyclerview.setAdapter(imgAdapter);



        return root;
    }

    private void fetchImages() {
        ApiService.getService().getDataImages().enqueue(new Callback<DataImage>() {
            @Override
            public void onResponse(Call<DataImage> call,
                                   Response<DataImage> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dataImage = response.body();
                    listImage.addAll(dataImage.getImageModel());

                    imgAdapter.notifyDataSetChanged();

                }
                imgAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<DataImage> call, Throwable t) {
                Log.d(HomeFragment.class.getSimpleName(), "error " + t);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}