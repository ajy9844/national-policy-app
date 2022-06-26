package org.techtown.policy_briefing_app.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.policy_briefing_app.DataAdapter;
import org.techtown.policy_briefing_app.Local;
import org.techtown.policy_briefing_app.R;
import org.techtown.policy_briefing_app.result_data;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private ArrayList<result_data> list = new ArrayList<>();
    private List<Local> servList;

    private GalleryViewModel galleryViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);




        return root;
    }


}