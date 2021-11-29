package com.example.pinapple.ui.dashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.pinapple.R;
import com.example.pinapple.adapters.VideoAdapter;
import com.example.pinapple.databinding.FragmentDashboardBinding;
import com.example.pinapple.models.DataVideo;
import com.example.pinapple.service.ApiService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    ProgressBar progressBar;
    List<DataVideo.VideoModel> mediaObjects = new ArrayList<>();
    DataVideo dataVideo;
    VideoAdapter videoAdapter;
    RecyclerView vidRecyclerview;
    SwipeRefreshLayout refreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        refreshLayout = root.findViewById(R.id.swipeLayout);

        progressBar = root.findViewById(R.id.progressBar);
        vidRecyclerview = root.findViewById(R.id.video_recyclerView);
        vidRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoAdapter(mediaObjects, getContext());

        loadItems();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
            }
        });

        vidRecyclerview.setAdapter(videoAdapter);
        return root;
    }

    private void  fetchVideo(){
        ApiService.getService().getDataVideos().enqueue(new Callback<DataVideo>() {

            @Override
            public void onResponse(Call<DataVideo> call, Response<DataVideo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dataVideo = response.body();
                    mediaObjects.addAll(dataVideo.getData());
                }
                videoAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<DataVideo> call, Throwable t) {
                Log.d(DashboardFragment.class.getSimpleName(), "error " + t);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadItems() {
        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
            mediaObjects.clear();
            videoAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out));
            videoAdapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            fetchVideo();
            mediaObjects.add(new DataVideo.VideoModel(1,"Pineapple", "sds","dad", "https://ba2e-2402-800-620e-c0f2-f11c-9c7e-c705-c4f9.ngrok.io/media/album/videos/detect/test_detect.mp4", "/media/album/videos/thumbnail/1631634900.jpg"));
            mediaObjects.add(new DataVideo.VideoModel(1,"Pineapple", "sds","dad", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "/media/album/videos/thumbnail/1631634900.jpg"));

            return null;
        }
    }
}